// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: Capacity.proto
// Protobuf Java Version: 4.29.1

public final class CapacityOuterClass {
  private CapacityOuterClass() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 1,
      /* suffix= */ "",
      CapacityOuterClass.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CapacityOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Capacity)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 subscriber_count = 1;</code>
     * @return The subscriberCount.
     */
    int getSubscriberCount();
  }
  /**
   * Protobuf type {@code Capacity}
   */
  public static final class Capacity extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:Capacity)
      CapacityOrBuilder {
  private static final long serialVersionUID = 0L;
    static {
      com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
        com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
        /* major= */ 4,
        /* minor= */ 29,
        /* patch= */ 1,
        /* suffix= */ "",
        Capacity.class.getName());
    }
    // Use Capacity.newBuilder() to construct.
    private Capacity(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
    }
    private Capacity() {
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CapacityOuterClass.internal_static_Capacity_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CapacityOuterClass.internal_static_Capacity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CapacityOuterClass.Capacity.class, CapacityOuterClass.Capacity.Builder.class);
    }

    public static final int SUBSCRIBER_COUNT_FIELD_NUMBER = 1;
    private int subscriberCount_ = 0;
    /**
     * <code>int32 subscriber_count = 1;</code>
     * @return The subscriberCount.
     */
    @java.lang.Override
    public int getSubscriberCount() {
      return subscriberCount_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (subscriberCount_ != 0) {
        output.writeInt32(1, subscriberCount_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (subscriberCount_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, subscriberCount_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof CapacityOuterClass.Capacity)) {
        return super.equals(obj);
      }
      CapacityOuterClass.Capacity other = (CapacityOuterClass.Capacity) obj;

      if (getSubscriberCount()
          != other.getSubscriberCount()) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + SUBSCRIBER_COUNT_FIELD_NUMBER;
      hash = (53 * hash) + getSubscriberCount();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static CapacityOuterClass.Capacity parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static CapacityOuterClass.Capacity parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static CapacityOuterClass.Capacity parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static CapacityOuterClass.Capacity parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static CapacityOuterClass.Capacity parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static CapacityOuterClass.Capacity parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(CapacityOuterClass.Capacity prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Capacity}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Capacity)
        CapacityOuterClass.CapacityOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return CapacityOuterClass.internal_static_Capacity_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return CapacityOuterClass.internal_static_Capacity_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                CapacityOuterClass.Capacity.class, CapacityOuterClass.Capacity.Builder.class);
      }

      // Construct using CapacityOuterClass.Capacity.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        subscriberCount_ = 0;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return CapacityOuterClass.internal_static_Capacity_descriptor;
      }

      @java.lang.Override
      public CapacityOuterClass.Capacity getDefaultInstanceForType() {
        return CapacityOuterClass.Capacity.getDefaultInstance();
      }

      @java.lang.Override
      public CapacityOuterClass.Capacity build() {
        CapacityOuterClass.Capacity result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public CapacityOuterClass.Capacity buildPartial() {
        CapacityOuterClass.Capacity result = new CapacityOuterClass.Capacity(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(CapacityOuterClass.Capacity result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.subscriberCount_ = subscriberCount_;
        }
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof CapacityOuterClass.Capacity) {
          return mergeFrom((CapacityOuterClass.Capacity)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(CapacityOuterClass.Capacity other) {
        if (other == CapacityOuterClass.Capacity.getDefaultInstance()) return this;
        if (other.getSubscriberCount() != 0) {
          setSubscriberCount(other.getSubscriberCount());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 8: {
                subscriberCount_ = input.readInt32();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private int subscriberCount_ ;
      /**
       * <code>int32 subscriber_count = 1;</code>
       * @return The subscriberCount.
       */
      @java.lang.Override
      public int getSubscriberCount() {
        return subscriberCount_;
      }
      /**
       * <code>int32 subscriber_count = 1;</code>
       * @param value The subscriberCount to set.
       * @return This builder for chaining.
       */
      public Builder setSubscriberCount(int value) {

        subscriberCount_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>int32 subscriber_count = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearSubscriberCount() {
        bitField0_ = (bitField0_ & ~0x00000001);
        subscriberCount_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Capacity)
    }

    // @@protoc_insertion_point(class_scope:Capacity)
    private static final CapacityOuterClass.Capacity DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new CapacityOuterClass.Capacity();
    }

    public static CapacityOuterClass.Capacity getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Capacity>
        PARSER = new com.google.protobuf.AbstractParser<Capacity>() {
      @java.lang.Override
      public Capacity parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Capacity> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Capacity> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public CapacityOuterClass.Capacity getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Capacity_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Capacity_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016Capacity.proto\"$\n\010Capacity\022\030\n\020subscrib" +
      "er_count\030\001 \001(\005b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Capacity_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Capacity_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Capacity_descriptor,
        new java.lang.String[] { "SubscriberCount", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
